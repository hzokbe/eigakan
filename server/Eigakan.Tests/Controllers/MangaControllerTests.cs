using Eigakan.Controllers;
using Eigakan.DTO;
using Eigakan.Enums;
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
}
