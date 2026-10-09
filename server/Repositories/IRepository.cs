namespace Eigakan.Repositories;

public interface IRepository<T>
{
    public Task<List<T>> GetAllAsync();
}
