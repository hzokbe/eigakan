@extends('layouts.page')

@section('content')
    <main class="bg-neutral-900 flex gap-4 m-4 p-4">
        <section>
            <img alt="{{$anime->title}}" src="{{$anime->image_source}}"/>
        </section>
        <section class="flex flex-1 flex-col gap-4">
            <section>
                <h2 class="font-bold text-2xl text-justify text-neutral-50">
                    {{ $anime->title }}
                </h2>
                <h3 class="font-bold text-neutral-50 text-md">{{ $anime->japanese_title }}</h3>
            </section>
            <section class="flex flex-1 flex-col gap-2">
                <h4 class="text-neutral-50 text-xl">Synopsis</h4>
                <hr class="text-neutral-50">
                <p class="text-justify text-neutral-50 text-md">
                    {{$anime->synopsis}}
                </p>
            </section>
            <section class="flex gap-4 text-sm">
                <p class="text-neutral-50">Score: {{$anime->score}}</p>
                @unless($anime->type == 'Movie')
                    <p class="text-neutral-50">Episodes: {{$anime->episodes}}</p>
                @endunless
                <p class="text-neutral-50">{{$anime->type}}</p>
            </section>
            <p class="text-neutral-50 text-sm">
                Aired:
                {{ sprintf('%s %d, %d', $anime->aired_from->translatedFormat('F'), $anime->aired_from->day, $anime->aired_from->year) }}
                to
                {{ sprintf('%s %d, %d', $anime->aired_to->translatedFormat('F'), $anime->aired_to->day, $anime->aired_from->year) }}
            </p>
            <p class="text-neutral-50 text-sm">{{$anime->status}}</p>
        </section>
    </main>
@endsection
