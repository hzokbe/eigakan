using Eigakan.DTO;
using Microsoft.AspNetCore.Mvc;

namespace Eigakan.Controllers;

public interface IAnimeController
{
    public Task<ActionResult<List<AnimeResponse>>> GetAllAsync();
}
