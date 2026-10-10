using Eigakan.DTO;
using Eigakan.Models;
using Eigakan.Repositories;

namespace Eigakan.Services;

public class MangaService(IMangaRepository repository) : IMangaService
{
    public async Task<List<MangaResponse>> GetAllAsync()
    {
        return [.. (await repository.GetAllAsync()).Select(ToResponse)];
    }

    public async Task<MangaResponse?> GetByIdAsync(Guid id)
    {
        var manga = await repository.GetByIdAsync(id);

        return manga == null ? null : ToResponse(manga);
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
}
