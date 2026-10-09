using Eigakan.Data;
using Eigakan.Models;
using Eigakan.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Eigakan.Tests.Repositories;

public class AnimeRepositoryUnitTests
{
    private static DbContextOptions<AppDbContext> CreateOptions()
    {
        return new DbContextOptionsBuilder<AppDbContext>().UseInMemoryDatabase(Guid.NewGuid().ToString()).Options;
    }

    private static async Task SeedAsync(DbContextOptions<AppDbContext> options, params Anime[] animes)
    {
        await using var context = new AppDbContext(options);

        context.Animes.AddRange(animes);

        await context.SaveChangesAsync();
    }

    private static Anime CreateAnime(string title, decimal score)
    {
        return new Anime
        {
            Id = Guid.NewGuid(),
            Title = title,
            Score = score
        };
    }

    [Fact]
    public async Task GetAllAsync_ReturnsEmptyList_WhenThereAreNoAnimes()
    {
        await using var context = new AppDbContext(CreateOptions());

        var repository = new AnimeRepository(context);

        var result = await repository.GetAllAsync();

        Assert.Empty(result);
    }

    [Fact]
    public async Task GetAllAsync_ReturnsAnimesOrderedByScore()
    {
        var options = CreateOptions();

        await SeedAsync(options, CreateAnime("Steins;Gate", 9.07m),
            CreateAnime("Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", 8.40m),
            CreateAnime("Sousou no Frieren", 9.25m));

        await using var context = new AppDbContext(options);

        var repository = new AnimeRepository(context);

        var result = await repository.GetAllAsync();

        string[] expected =
            ["Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", "Steins;Gate", "Sousou no Frieren"];

        Assert.Equal(expected, result.Select(a => a.Title));
    }

    [Fact]
    public async Task GetAllAsync_DoesNotTrackReturnedEntities()
    {
        var options = CreateOptions();

        await SeedAsync(options, CreateAnime("Steins;Gate", 9.07m));

        await using var context = new AppDbContext(options);

        var repository = new AnimeRepository(context);

        await repository.GetAllAsync();

        Assert.Empty(context.ChangeTracker.Entries());
    }
}
