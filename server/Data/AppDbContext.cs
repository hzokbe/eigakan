using Eigakan.Models;
using Microsoft.EntityFrameworkCore;

namespace Eigakan.Data;

public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
{
    public DbSet<Anime> Animes => Set<Anime>();

    public DbSet<Manga> Mangas => Set<Manga>();

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Anime>(entity =>
        {
            entity.Property(a => a.Type).HasConversion<string>();

            entity.Property(a => a.Status).HasConversion<string>();

            entity.Property(a => a.Score).HasPrecision(4, 2);
        });

        modelBuilder.Entity<Manga>(entity =>
        {
            entity.Property(a => a.Status).HasConversion<string>();

            entity.Property(a => a.Score).HasPrecision(4, 2);
        });
    }
}
