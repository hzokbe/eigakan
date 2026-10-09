using Eigakan.Data;
using Eigakan.Models;
using Microsoft.EntityFrameworkCore;

namespace Eigakan.Repositories;

public class MangaRepository(AppDbContext context) : IMangaRepository
{
    public async Task<List<Manga>> GetAllAsync()
    {
        return await context.Mangas.AsNoTracking().OrderByDescending(m => m.Score).ToListAsync();
    }
}
