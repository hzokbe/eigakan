using Eigakan.Enums;
using Eigakan.Models;
using Eigakan.Repositories;
using Eigakan.Services;
using Moq;

namespace Eigakan.Tests.Services;

public class AnimeServiceUnitTests
{
    private readonly Mock<IAnimeRepository> _repository = new();

    private readonly AnimeService _service;

    public AnimeServiceUnitTests()
    {
        _service = new AnimeService(_repository.Object);
    }

    [Fact]
    public async Task GetAllAsync_WhenRepositoryIsEmpty_ReturnsEmptyList()
    {
        _repository.Setup(r => r.GetAllAsync()).ReturnsAsync([]);

        var result = await _service.GetAllAsync();

        Assert.Empty(result);
    }

    [Fact]
    public async Task GetAllAsync_MapsAllFieldsCorrectly()
    {
        var id = Guid.NewGuid();

        var anime = new Anime
        {
            Id = id,
            Title = "Cowboy Bebop",
            JapaneseTitle = "カウボーイビバップ",
            Synopsis = "Space bounty hunters",
            Type = AnimeType.TV,
            Episodes = 26,
            Status = AnimeStatus.FinishedAiring,
            AiredFrom = new DateOnly(1998, 4, 3),
            AiredTo = new DateOnly(1999, 4, 24),
            Score = 8.75m,
            ImageSource = "/images/anime/" + id
        };

        _repository.Setup(r => r.GetAllAsync()).ReturnsAsync([anime]);

        var result = await _service.GetAllAsync();

        var response = Assert.Single(result);

        Assert.Equal(anime.Id, response.Id);

        Assert.Equal(anime.Title, response.Title);

        Assert.Equal(anime.JapaneseTitle, response.JapaneseTitle);

        Assert.Equal(anime.Synopsis, response.Synopsis);

        Assert.Equal(anime.Type, response.Type);

        Assert.Equal(anime.Episodes, response.Episodes);

        Assert.Equal(anime.Status, response.Status);

        Assert.Equal(anime.AiredFrom, response.AiredFrom);

        Assert.Equal(anime.AiredTo, response.AiredTo);

        Assert.Equal(anime.Score, response.Score);

        Assert.Equal(anime.ImageSource, response.ImageSource);
    }

    [Fact]
    public async Task GetAllAsync_CallsRepositoryOnce()
    {
        _repository.Setup(r => r.GetAllAsync()).ReturnsAsync([]);

        await _service.GetAllAsync();

        _repository.Verify(r => r.GetAllAsync(), Times.Once);
    }

    [Fact]
    public async Task GetByIdAsync_ReturnsNull()
    {
        var id = Guid.NewGuid();

        var anime = new Anime
        {
            Id = id,
            Title = "Cowboy Bebop",
            JapaneseTitle = "カウボーイビバップ",
            Synopsis = "Space bounty hunters",
            Type = AnimeType.TV,
            Episodes = 26,
            Status = AnimeStatus.FinishedAiring,
            AiredFrom = new DateOnly(1998, 4, 3),
            AiredTo = new DateOnly(1999, 4, 24),
            Score = 8.75m,
            ImageSource = "/images/anime/" + id
        };

        _repository.Setup(r => r.GetByIdAsync(id)).ReturnsAsync(anime);

        var anotherId = Guid.NewGuid();

        var result = await _service.GetByIdAsync(anotherId);

        Assert.Null(result);
    }

    [Fact]
    public async Task GetByIdAsync_ReturnsAnime()
    {
        var id = Guid.NewGuid();

        var anime = new Anime
        {
            Id = id,
            Title = "Cowboy Bebop",
            JapaneseTitle = "カウボーイビバップ",
            Synopsis = "Space bounty hunters",
            Type = AnimeType.TV,
            Episodes = 26,
            Status = AnimeStatus.FinishedAiring,
            AiredFrom = new DateOnly(1998, 4, 3),
            AiredTo = new DateOnly(1999, 4, 24),
            Score = 8.75m,
            ImageSource = "/images/anime/" + id
        };

        _repository.Setup(r => r.GetByIdAsync(id)).ReturnsAsync(anime);

        var result = await _service.GetByIdAsync(id);

        Assert.NotNull(result);

        Assert.Equal(anime.Id, result.Id);
    }

    [Fact]
    public async Task GetByIdAsync_CallsRepositoryOnce()
    {
        var id = Guid.NewGuid();

        var anime = new Anime
        {
            Id = id,
            Title = "Cowboy Bebop",
            JapaneseTitle = "カウボーイビバップ",
            Synopsis = "Space bounty hunters",
            Type = AnimeType.TV,
            Episodes = 26,
            Status = AnimeStatus.FinishedAiring,
            AiredFrom = new DateOnly(1998, 4, 3),
            AiredTo = new DateOnly(1999, 4, 24),
            Score = 8.75m,
            ImageSource = "/images/anime/" + id
        };

        _repository.Setup(r => r.GetByIdAsync(id)).ReturnsAsync(anime);

        await _service.GetByIdAsync(id);

        _repository.Verify(r => r.GetByIdAsync(id), Times.Once);
    }
}
