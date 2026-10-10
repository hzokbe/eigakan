using Eigakan.Controllers;
using Eigakan.DTO;
using Eigakan.Enums;
using Eigakan.Models;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;
using Moq;

namespace Eigakan.Tests.Controllers;

public class AnimeControllerTests
{
    private readonly AnimeController _controller;

    private readonly Mock<IAnimeService> _service = new();

    public AnimeControllerTests()
    {
        _controller = new AnimeController(_service.Object);
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

    private static AnimeResponse ToResponse(Anime anime)
    {
        return new AnimeResponse(
            anime.Id,
            anime.Title,
            anime.JapaneseTitle,
            anime.Synopsis,
            anime.Type,
            anime.Episodes,
            anime.Status,
            anime.AiredFrom,
            anime.AiredTo,
            anime.Score,
            anime.ImageSource
        );
    }

    [Fact]
    public async Task GetAllAsync_ReturnsOkWithServiceResult()
    {
        var id = Guid.NewGuid();

        var animes = new List<AnimeResponse>
        {
            new(id, "Cowboy Bebop", "カウボーイビバップ", "Space bounty hunters", AnimeType.TV, 26,
                AnimeStatus.FinishedAiring,
                new DateOnly(1998, 4, 3), new DateOnly(1999, 4, 24), 8.75m,
                "/images/anime/" + id)
        };

        _service.Setup(s => s.GetAllAsync()).ReturnsAsync(animes);

        var result = await _controller.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Equal(200, ok.StatusCode);

        Assert.Same(animes, ok.Value);
    }

    [Fact]
    public async Task GetAllAsync_WhenServiceReturnsEmpty_ReturnsOkWithEmptyList()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        var result = await _controller.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Empty(Assert.IsType<List<AnimeResponse>>(ok.Value));
    }

    [Fact]
    public async Task GetAllAsync_CallsServiceOnce()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        await _controller.GetAllAsync();

        _service.Verify(s => s.GetAllAsync(), Times.Once);
    }

    [Fact]
    public async Task GetByIdAsync_WhenServiceReturnsAnime_ReturnsOk()
    {
        var id = Guid.NewGuid();

        var anime = CreateAnime("Steins;Gate", 9.07m);

        var response = ToResponse(anime);

        _service.Setup(s => s.GetByIdAsync(id)).ReturnsAsync(response);

        var result = await _controller.GetByIdAsync(id);

        Assert.IsType<OkObjectResult>(result.Result);
    }

    [Fact]
    public async Task GetByIdAsync_WhenServiceReturnsEmpty_ReturnsNotFound()
    {
        var id = Guid.NewGuid();

        AnimeResponse? response = null;

        _service.Setup(s => s.GetByIdAsync(id)).ReturnsAsync(response);

        var result = await _controller.GetByIdAsync(id);

        Assert.IsType<NotFoundResult>(result.Result);
    }
}
