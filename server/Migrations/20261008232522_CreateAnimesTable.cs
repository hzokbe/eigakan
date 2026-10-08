using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Eigakan.Migrations;

/// <inheritdoc />
public partial class CreateAnimesTable : Migration
{
    /// <inheritdoc />
    protected override void Up(MigrationBuilder migrationBuilder)
    {
        migrationBuilder.CreateTable(
            "Animes",
            table => new
            {
                Id = table.Column<Guid>("uuid", nullable: false),
                Title = table.Column<string>("text", nullable: false),
                JapaneseTitle = table.Column<string>("text", nullable: true),
                Synopsis = table.Column<string>("text", nullable: true),
                Type = table.Column<string>("text", nullable: false),
                Episodes = table.Column<int>("integer", nullable: true),
                Status = table.Column<string>("text", nullable: false),
                AiredFrom = table.Column<DateOnly>("date", nullable: true),
                AiredTo = table.Column<DateOnly>("date", nullable: true),
                Score = table.Column<decimal>("numeric(4,2)", precision: 4, scale: 2, nullable: true),
                ImageSource = table.Column<string>("text", nullable: true)
            },
            constraints: table => { table.PrimaryKey("PK_Animes", x => x.Id); });
    }

    /// <inheritdoc />
    protected override void Down(MigrationBuilder migrationBuilder)
    {
        migrationBuilder.DropTable(name: "Animes");
    }
}
