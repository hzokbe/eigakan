using Eigakan.Enums;
using Eigakan.Models;
using Eigakan.Repositories;
using Eigakan.Services;
using Moq;

namespace Eigakan.Tests.Services;

public class MangaServiceUnitTests
{
    private readonly Mock<IMangaRepository> _repository = new();

    private readonly MangaService _service;

    public MangaServiceUnitTests()
    {
        _service = new MangaService(_repository.Object);
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

        var manga = new Manga
        {
            Id = id,
            Title = "Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen",
            JapaneseTitle = "かぐや様は告らせたい～天才たちの恋愛頭脳戦～",
            Synopsis = "Love is war!",
            Chapters = 281,
            Volumes = 28,
            Status = MangaStatus.Finished,
            PublishedFrom = new DateOnly(2015, 5, 19),
            PublishedTo = new DateOnly(2022, 11, 2),
            Score = 8.88m,
            ImageSource = "/images/manga/" + id
        };

        _repository.Setup(r => r.GetAllAsync()).ReturnsAsync([manga]);

        var result = await _service.GetAllAsync();

        var response = Assert.Single(result);

        Assert.Equal(manga.Id, response.Id);

        Assert.Equal(manga.Title, response.Title);

        Assert.Equal(manga.JapaneseTitle, response.JapaneseTitle);

        Assert.Equal(manga.Synopsis, response.Synopsis);

        Assert.Equal(manga.Chapters, response.Chapters);

        Assert.Equal(manga.Volumes, response.Volumes);

        Assert.Equal(manga.Status, response.Status);

        Assert.Equal(manga.PublishedFrom, response.PublishedFrom);

        Assert.Equal(manga.PublishedTo, response.PublishedTo);

        Assert.Equal(manga.Score, response.Score);

        Assert.Equal(manga.ImageSource, response.ImageSource);
    }

    [Fact]
    public async Task GetAllAsync_CallsRepositoryOnce()
    {
        _repository.Setup(r => r.GetAllAsync()).ReturnsAsync([]);

        await _service.GetAllAsync();

        _repository.Verify(r => r.GetAllAsync(), Times.Once);
    }
}
