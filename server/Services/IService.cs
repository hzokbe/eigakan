namespace Eigakan.Services;

public interface IService<T>
{
    public Task<List<T>> GetAllAsync();

    public Task<T?> GetByIdAsync(Guid id);
}
