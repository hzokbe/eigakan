using Eigakan.Data;
using Eigakan.Models;
using Eigakan.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Eigakan.Tests.Repositories;

public class MangaRepositoryUnitTests
{
    private static DbContextOptions<AppDbContext> CreateOptions()
    {
        return new DbContextOptionsBuilder<AppDbContext>().UseInMemoryDatabase(Guid.NewGuid().ToString()).Options;
    }

    private static async Task SeedAsync(DbContextOptions<AppDbContext> options, params Manga[] mangas)
    {
        await using var context = new AppDbContext(options);

        context.Mangas.AddRange(mangas);

        await context.SaveChangesAsync();
    }

    private static Manga CreateManga(string title, decimal score)
    {
        return new Manga
        {
            Id = Guid.NewGuid(),
            Title = title,
            Score = score
        };
    }

    [Fact]
    public async Task GetAllAsync_ReturnsEmptyList_WhenThereAreNoMangas()
    {
        await using var context = new AppDbContext(CreateOptions());

        var repository = new MangaRepository(context);

        var result = await repository.GetAllAsync();

        Assert.Empty(result);
    }

    [Fact]
    public async Task GetAllAsync_ReturnsMangasOrderedByScore()
    {
        var options = CreateOptions();

        await SeedAsync(options, CreateManga("Steins;Gate", 8.07m),
            CreateManga("Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", 8.88m),
            CreateManga("Sousou no Frieren", 8.87m));

        await using var context = new AppDbContext(options);

        var repository = new MangaRepository(context);

        var result = await repository.GetAllAsync();

        string[] expected =
            ["Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", "Sousou no Frieren", "Steins;Gate"];

        Assert.Equal(expected, result.Select(a => a.Title));
    }

    [Fact]
    public async Task GetAllAsync_DoesNotTrackReturnedEntities()
    {
        var options = CreateOptions();

        await SeedAsync(options, CreateManga("Steins;Gate", 8.07m));

        await using var context = new AppDbContext(options);

        var repository = new MangaRepository(context);

        await repository.GetAllAsync();

        Assert.Empty(context.ChangeTracker.Entries());
    }
}
