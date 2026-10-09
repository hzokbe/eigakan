using Eigakan.Models;

namespace Eigakan.Repositories;

public interface IAnimeRepository
{
    public Task<List<Anime>> GetAllAsync();
}
