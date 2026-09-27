<?php

namespace Database\Seeders;

use App\Models\Anime;
use App\Models\Genre;
use Illuminate\Database\Seeder;

class AnimeSeeder extends Seeder
{
    public function run(): void
    {
        $fantasy = Genre::firstOrCreate(['name' => 'Fantasy']);

        $adventure = Genre::firstOrCreate(['name' => 'Adventure']);

        $drama = Genre::firstOrCreate(['name' => 'Drama']);

        $comedy = Genre::firstOrCreate(['name' => 'Comedy']);

        $romance = Genre::firstOrCreate(['name' => 'Romance']);

        $sciFi = Genre::firstOrCreate(['name' => 'Sci-Fi']);

        $thriller = Genre::firstOrCreate(['name' => 'Thriller']);

        $frieren = Anime::firstOrCreate([
            'title' => 'Sousou no Frieren',
            'japanese_title' => '葬送のフリーレン',
            'synopsis' => 'Uma maga élfica revisita, após o fim da jornada heroica, os lugares por onde passou com seus antigos companheiros.',
            'type' => 'TV',
            'episodes' => 28,
            'status' => 'Finished Airing',
            'aired_from' => '2023-09-29',
            'aired_to' => '2024-03-22',
            'score' => 9.25,
            'image_source' => 'https://cdn.myanimelist.net/images/anime/1015/138006.jpg',
        ]);

        $frieren->genres()->attach([$fantasy->id, $adventure->id, $drama->id]);

        $kaguyaS1 = Anime::firstOrCreate([
            'title' => 'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen',
            'japanese_title' => 'かぐや様は告らせたい〜天才たちの恋愛頭脳戦〜',
            'synopsis' => 'Os dois representantes de destaque da Academia Shuchiin escondem sentimentos mútuos, mas nenhum admite primeiro por orgulho, travando um jogo psicológico de conquista.',
            'type' => 'TV',
            'episodes' => 12,
            'status' => 'Finished Airing',
            'aired_from' => '2019-01-12',
            'aired_to' => '2019-03-30',
            'score' => 8.40,
            'image_source' => 'https://cdn.myanimelist.net/images/anime/1295/106551.jpg',
        ]);

        $kaguyaS1->genres()->attach([$comedy->id, $romance->id]);

        $steinsGate = Anime::firstOrCreate([
            'title' => 'Steins;Gate',
            'japanese_title' => 'STEINS;GATE',
            'synopsis' => 'Um cientista excêntrico e seus amigos descobrem, por acaso, uma forma de enviar mensagens ao passado através de um micro-ondas modificado, desencadeando consequências perigosas ao alterarem a linha do tempo.',
            'type' => 'TV',
            'episodes' => 24,
            'status' => 'Finished Airing',
            'aired_from' => '2011-04-06',
            'aired_to' => '2011-09-14',
            'score' => 9.07,
            'image_source' => 'https://cdn.myanimelist.net/images/anime/1935/127974.jpg',
        ]);

        $steinsGate->genres()->attach([$sciFi->id, $thriller->id, $drama->id]);

        $kaguyaS2 = Anime::firstOrCreate([
            'title' => 'Kaguya-sama wa Kokurasetai? Tensai-tachi no Renai Zunousen',
            'japanese_title' => 'かぐや様は告らせたい?〜天才たちの恋愛頭脳戦〜',
            'synopsis' => 'Com o segundo semestre letivo em curso, Kaguya e Miyuki enfrentam o fim do mandato do conselho estudantil, colocando em risco os poucos momentos que ainda têm juntos, enquanto uma disputada eleição decide quem assumirá a presidência.',
            'type' => 'TV',
            'episodes' => 12,
            'status' => 'Finished Airing',
            'aired_from' => '2020-04-11',
            'aired_to' => '2020-06-27',
            'score' => 8.61,
            'image_source' => 'https://cdn.myanimelist.net/images/anime/1764/106659.jpg',
        ]);

        $kaguyaS2->genres()->attach([$comedy->id, $romance->id]);
    }
}
