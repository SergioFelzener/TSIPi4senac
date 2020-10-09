<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Edit Product -> ({{$product->name}})
        </h2>
    </x-slot>

    <form method="POST" class="border-blue-500 border-opacity-0 max-w-7xl mx-auto px-6 py-6 bg-gray-100 shadow mt-4 rounded-md" action="{{route('admin.products.update', $product->id)}}">
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
        <div class="mt-5 mr-12 float-right">
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded-md shadow">Save Product</button>
        </div>





    </form>



</x-app-layout>
