<div class="bg-neutral-900 flex gap-4 p-4">
    <div>
        <a href="/{{$anime->id}}">
            <img alt="{{$anime->title}}" src="{{$anime->image_source}}" width="64px"/>
        </a>
    </div>
    <div class="flex-1">
        <h2 class="font-bold text-2xl text-justify text-neutral-50">
            <a href="/{{$anime->id}}">
                {{ $anime->title }}
            </a>
            @unless($anime->aired_from == null)
                ({{$anime->aired_from->year}})
            @endunless
        </h2>
        <p class="text-justify text-neutral-50">
            {{$anime->synopsis}}
        </p>
        <div class="flex gap-4">
            <div>
                <p class="text-neutral-50">Score: {{$anime->score}}</p>
            </div>
            @unless($anime->type == 'Movie')
                <div>
                    <p class="text-neutral-50">Episodes: {{$anime->episodes}}</p>
                </div>
            @endunless
        </div>
    </div>
</div>
