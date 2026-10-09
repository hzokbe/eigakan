using Eigakan.DTO;
using Eigakan.Services;
using Microsoft.AspNetCore.Mvc;

namespace Eigakan.Controllers;

[ApiController]
[Route("/mangas")]
public class MangaController(IMangaService service) : ControllerBase, IMangaController
{
    [HttpGet]
    public async Task<ActionResult<List<MangaResponse>>> GetAllAsync()
    {
        return Ok(await service.GetAllAsync());
    }
}
