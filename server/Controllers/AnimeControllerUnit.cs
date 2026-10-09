using Eigakan.DTO;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;

namespace Eigakan.Controllers;

[ApiController]
[Route("/animes")]
public class AnimeControllerUnit(IAnimeService service) : ControllerBase, IAnimeController
{
    public async Task<ActionResult<List<AnimeResponse>>> GetAllAsync()
    {
        return Ok(await service.GetAllAsync());
    }
}
