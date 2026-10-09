using Eigakan.Data;
using Eigakan.Models;
using Microsoft.EntityFrameworkCore;

namespace Eigakan.Repositories;

public class AnimeRepository(AppDbContext context) : IAnimeRepository
{
    public async Task<List<Anime>> GetAllAsync()
    {
        return await context.Animes.AsNoTracking().OrderByDescending(a => a.Score).ToListAsync();
    }

    public async Task<Anime?> GetByIdAsync(Guid id)
    {
        return await context.Animes.FirstOrDefaultAsync(a => a.Id == id);
    }
}
