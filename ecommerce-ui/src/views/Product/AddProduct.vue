<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4>Añadir Nuevo Producto</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form>
                    <div class="form-group">
                        <label>Categoria</label>
                        <select class="form-control" v-model="categoryId" required>
                            <option v-for="category in categories" :key="category.id"
                                    :value="category.id">{{ category.categoryName }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" v-model="name" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Descripción</label>
                        <input type="text" v-model="description" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Imagen Url</label>
                        <input type="text"  v-model="imageURL" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Precio</label>
                        <input type="number" v-model="price" class="form-control" >
                    </div>
                    <button type="button" class="btn btn-primary" @click="addProduct">Añadir Producto</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>

<!--        Form-->
    </div>
</template>
<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        props: ["baseURL", "categories"],
        data() {
            return {
                id: null,
                categoryId: null,
                name: null,
                description: null,
                imageURL: null,
                price: null
            }
        },
        methods: {
            addProduct() {
                const newProduct = {
                    categoryId: this.categoryId,
                    description: this.description,
                    name: this.name,
                    imageURL: this.imageURL,
                    price: this.price
                };

                axios.post(this.baseURL+"product/add", newProduct)
                .then(() => {
                    this.$router.push({name: 'AdminProduct'});
                    swal({
                        text: "Product added",
                        icon: "success"
                    })
                }).catch((err)=> {
                    console.log("err", err);
                })


            }
        }
    }
</script>
