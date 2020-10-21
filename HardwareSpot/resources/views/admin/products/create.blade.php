<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Create Product
        </h2>
    </x-slot>

    <form method="POST" class="border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md" enctype="multipart/form-data" action="{{route('admin.products.store')}}">
        @csrf
        <div class="w-full pl-3">
            <label class="uppercase text-black font-bold text-sm">Name</label>
            <input type="text" name="name" id="name" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="">
            @error('name')
            <div class="mt-1 ml-2 text-sm text-red-600">
                {{$message}}
            </div>
            @enderror
        </div>
        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Description</label>
            <textarea type="text" name="description" id="description" class="w-full bg-gray-100 text-gray-800 focus:bg-blue-100 focus:outline-none bg-gray-200 shadow rounded-md py-2 px-2" value=""></textarea>
            @error('description')
            <div class="ml-2 text-sm text-red-600">
                {{$message}}
            </div>
            @enderror
        </div>
        <div class="flex">
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Price</label>
                <input style="text-align:end;" type="decimal" name="price" id="price">
                @error('price')
                <div class="mt-4 ml-8 text-sm text-red-600">
                    {{$message}}
                </div>
                @enderror
            </div>
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Descount</label>
                <input style="text-align:end;" type="decimal" name="descount" id="descount">
                @error('descount')
                <div class="mt-4 ml-12 text-sm text-red-600">
                    {{$message}}
                </div>
                @enderror
            </div>
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Quantity</label>
                <input style="text-align:end;" type="decimal" name="quantity" id="quantity">
                @error('quantity')
                <div class="mt-4 ml-14 text-sm text-red-600">
                    {{$message}}
                </div>
                @enderror
            </div>
        </div>

        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Product Photos</label>
            <input type="file" name="photos[]" id="name" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="" multiple>
            @error('photos.*')
            <div class="mt-4 ml-2 text-sm text-red-600">
                {{$message}}
            </div>
            @enderror
        </div>

        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Slug</label>
            <input type="text" name="slug" id="slug" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="">
        </div>
        <div class="select-all px-4 border-blue-500 border-opacity-100 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
            <label for="categories">Categories</label>
            <select name="categories[]" id="" class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500" multiple>
                @foreach ($categories as $category)
                <option value="{{$category->id}}">{{$category->name}}</option>
                @endforeach
            </select>
        </div>
        <div class="mt-5 mr-12 float-right">
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded-md shadow">Create Product</button>
        </div>
    </form>

</x-app-layout>