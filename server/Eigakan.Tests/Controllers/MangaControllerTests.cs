using Eigakan.Controllers;
using Eigakan.DTO;
using Eigakan.Enums;
using Eigakan.Models;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;
using Moq;

namespace Eigakan.Tests.Controllers;

public class MangaControllerTests
{
    private readonly MangaController _controller;

    private readonly Mock<IMangaService> _service = new();

    public MangaControllerTests()
    {
        _controller = new MangaController(_service.Object);
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

    private static MangaResponse ToResponse(Manga manga)
    {
        return new MangaResponse(
            manga.Id,
            manga.Title,
            manga.JapaneseTitle,
            manga.Synopsis,
            manga.Chapters,
            manga.Volumes,
            manga.Status,
            manga.PublishedFrom,
            manga.PublishedTo,
            manga.Score,
            manga.ImageSource
        );
    }

    [Fact]
    public async Task GetAllAsync_ReturnsOkWithServiceResult()
    {
        var id = Guid.NewGuid();

        var mangas = new List<MangaResponse>
        {
            new(id, "Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", "かぐや様は告らせたい～天才たちの恋愛頭脳戦～",
                "Love is war!", 281, 28, MangaStatus.Finished, new DateOnly(2015, 5, 19), new DateOnly(2022, 11, 2),
                8.88m, "/images/manga/" + id)
        };

        _service.Setup(s => s.GetAllAsync()).ReturnsAsync(mangas);

        var result = await _controller.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Equal(200, ok.StatusCode);

        Assert.Same(mangas, ok.Value);
    }

    [Fact]
    public async Task GetAllAsync_WhenServiceReturnsEmpty_ReturnsOkWithEmptyList()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        var result = await _controller.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Empty(Assert.IsType<List<MangaResponse>>(ok.Value));
    }

    [Fact]
    public async Task GetAllAsync_CallsServiceOnce()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        await _controller.GetAllAsync();

        _service.Verify(s => s.GetAllAsync(), Times.Once);
    }

    [Fact]
    public async Task GetByIdAsync_WhenServiceReturnsManga_ReturnsOk()
    {
        var id = Guid.NewGuid();

        var anime = CreateManga("Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen", 8.88m);

        var response = ToResponse(anime);

        _service.Setup(s => s.GetByIdAsync(id)).ReturnsAsync(response);

        var result = await _controller.GetByIdAsync(id);

        Assert.IsType<OkObjectResult>(result.Result);
    }

    [Fact]
    public async Task GetByIdAsync_WhenServiceReturnsEmpty_ReturnsNotFound()
    {
        var id = Guid.NewGuid();

        MangaResponse? response = null;

        _service.Setup(s => s.GetByIdAsync(id)).ReturnsAsync(response);

        var result = await _controller.GetByIdAsync(id);

        Assert.IsType<NotFoundResult>(result.Result);
    }
}
