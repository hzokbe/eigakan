using Eigakan.DTO;

namespace Eigakan.Services;

public interface IAnimeService
{
    public Task<List<AnimeResponse>> GetAllAsync();
}
