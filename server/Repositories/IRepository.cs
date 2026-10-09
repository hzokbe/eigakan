namespace Eigakan.Repositories;

public interface IRepository<T>
{
    public Task<List<T>> GetAllAsync();

    public Task<T?> GetByIdAsync(Guid id);
}
