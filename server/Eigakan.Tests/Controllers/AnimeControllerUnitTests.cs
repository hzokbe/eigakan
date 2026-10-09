using Eigakan.Controllers;
using Eigakan.DTO;
using Eigakan.Enums;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;
using Moq;

namespace Eigakan.Tests.Controllers;

public class AnimeControllerUnitTests
{
    private readonly AnimeControllerUnit _controllerUnit;

    private readonly Mock<IAnimeService> _service = new();

    public AnimeControllerUnitTests()
    {
        _controllerUnit = new AnimeControllerUnit(_service.Object);
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

        var result = await _controllerUnit.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Equal(200, ok.StatusCode);

        Assert.Same(animes, ok.Value);
    }

    [Fact]
    public async Task GetAllAsync_WhenServiceReturnsEmpty_ReturnsOkWithEmptyList()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        var result = await _controllerUnit.GetAllAsync();

        var ok = Assert.IsType<OkObjectResult>(result.Result);

        Assert.Empty(Assert.IsType<List<AnimeResponse>>(ok.Value));
    }

    [Fact]
    public async Task GetAllAsync_CallsServiceOnce()
    {
        _service.Setup(s => s.GetAllAsync()).ReturnsAsync([]);

        await _controllerUnit.GetAllAsync();

        _service.Verify(s => s.GetAllAsync(), Times.Once);
    }
}
