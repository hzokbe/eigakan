using Eigakan.DTO;
using Eigakan.Models;
using Eigakan.Repositories;

namespace Eigakan.Services;

public class AnimeService(IAnimeRepository repository) : IAnimeService
{
    public async Task<List<AnimeResponse>> GetAllAsync()
    {
        return [.. (await repository.GetAllAsync()).Select(ToResponse)];
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
}
