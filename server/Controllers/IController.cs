using Microsoft.AspNetCore.Mvc;

namespace Eigakan.Controllers;

public interface IController<T>
{
    public Task<ActionResult<List<T>>> GetAllAsync();
}
