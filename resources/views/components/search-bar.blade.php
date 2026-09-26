@php
    $query = request('query', '');

    $sort = request('sort', 'score');

    $direction = request('direction', 'desc');
@endphp

<div class="bg-neutral-900 flex gap-4 p-4 text-neutral-50 text-sm">
    <form class="flex flex-1 flex-col gap-2" method="get" action="/animes">
        <input class="bg-neutral-50 flex-1 px-4 py-2 outline-none text-neutral-950" name="query"
               value="{{$query}}"
               placeholder="Search anime"/>
        <div class="flex flex-1 flex-nowrap gap-2 h-full justify-end">
            <select class="bg-neutral-800 cursor-pointer px-4 py-1 pr-2" name="sort">
                <option value="title" @selected($sort === 'title')>Title</option>
                <option value="score" @selected($sort === 'score')>Score</option>
                <option value="aired_from" @selected($sort === 'aired_from')>Aired at</option>
            </select>
            <select class="bg-neutral-800 cursor-pointer px-4 py-1" name="direction">
                <option value="asc" @selected($direction === 'asc')>Ascending</option>
                <option value="desc" @selected($direction === 'desc')>Descending</option>
            </select>
            <button class="bg-blue-600 cursor-pointer font-bold px-4 py-1 text-neutral-50" type="submit">Search
            </button>
        </div>
    </form>
</div>
