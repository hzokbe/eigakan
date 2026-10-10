using Eigakan.DTO;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;

namespace Eigakan.Controllers;

[ApiController]
[Route("/animes")]
public class AnimeController(IAnimeService service) : ControllerBase, IAnimeController
{
    [HttpGet]
    public async Task<ActionResult<List<AnimeResponse>>> GetAllAsync()
    {
        return Ok(await service.GetAllAsync());
    }

    [HttpGet("{id:guid}")]
    public async Task<ActionResult<AnimeResponse?>> GetByIdAsync(Guid id)
    {
        var anime = await service.GetByIdAsync(id);

        return anime == null ? NotFound() : Ok(anime);
    }
}
