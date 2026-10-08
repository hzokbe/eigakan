using Microsoft.EntityFrameworkCore;

namespace Eigakan.Data;

public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
{
}
