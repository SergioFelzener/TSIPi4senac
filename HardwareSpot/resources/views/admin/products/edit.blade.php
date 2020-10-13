<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Edit Product -> ({{$product->name}})
        </h2>
        @if (session('success'))
        <div class="bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md" role="alert">
            <div class="flex">
                <div class="py-1"><svg class="fill-current h-6 w-6 text-teal-500 mr-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                        <path d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z" /></svg></div>
                {{session('success')}}
            </div>
        </div>
        @endif
    </x-slot>

    <form method="POST" class="border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md" enctype="multipart/form-data" action="{{route('admin.products.update',['product' => $product->id])}}">
        <input type="hidden" name="_token" value="{{csrf_token()}}">
        @csrf
        @method('put')

        <div class="w-full pl-3">
            <label class="uppercase text-black font-bold text-sm">Name</label>
            <input type="text" name="name" id="name" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="{{$product->name}}">
        </div>
        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Description</label>
            <textarea type="text" name="description" id="description" class="w-full bg-gray-100 text-gray-800 focus:bg-blue-100 focus:outline-none bg-gray-200 shadow rounded-md py-2 px-2" value="">{{$product->description}}</textarea>
        </div>
        <div class="flex">
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Price</label>
                <input style="text-align:end;" type="decimal" name="price" id="price" value="{{$product->price}}">
            </div>
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Descount</label>
                <input style="text-align:end;" type="decimal" name="descount" id="descount" value="{{$product->descount}}">
            </div>
            <div class="px-4 border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
                <label for="price" class="uppercase text-black font-bold text-sm mr-4">Quantity</label>
                <input style="text-align:end;" type="decimal" name="quantity" id="quantity" value="{{$product->quantity}}">
            </div>

        </div>
        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Slug</label>
            <input type="text" name="slug" id="slug" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="{{$product->slug}}">
        </div>



        <div class="px-4 border-blue-500 border-opacity-100 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md">
            <label for="categories">Categories</label>
            <select name="categories" id="" class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                @foreach ($categories as $category)

                <option value="{{$category->id}}" @if ($product->categories->contains($category)) selected @endif>{{$category->name}}</option>


                @endforeach
            </select>
        </div>

        <div class="w-full pl-3 mt-3">
            <label class="uppercase text-black font-bold text-sm">Product Photos</label>
            <input type="file" name="photos[]" id="name" class="w-full bg-gray-100 text-gray-800 py-2 px-2 focus:bg-blue-100  focus:outline-none bg-gray-200 shadow rounded-md" value="" multiple>
        </div>

        <div class="mt-5 mr-12 float-right">
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded-md shadow">Update Product</button>
        </div>

    </form>

    <hr>

    <div class="w-full pl-3 mt-3 ml-5 px-4 lg:w-1/3 xl:w-1/4">
        @foreach($product->photos as $photo)
        <div class="flex rounded-b-lg shadow-2xl">
            <img src="{{asset('storage/' . $photo->image)}}" alt="" class="h-36 w-52 flex-shrink-0 py-2 mr-2 ml-2">
            <div class="ml-10 mt-14">
                <form action="{{route('admin.photo.remove')}}" method="POST">
                    @csrf
                    <input type="hidden" name="photoName" value="{{$photo->image}}">
                    <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded">Delete</button>
                </form>
            </div>
        </div>
        @endforeach
    </div>



</x-app-layout>
