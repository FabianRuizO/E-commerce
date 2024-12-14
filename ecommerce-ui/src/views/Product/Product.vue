ï»¿<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4> Productos </h4>
                <router-link v-if="isAdmin" :to="{name: 'AddProduct'}" style="float: right">
                    <button class="btn" >
                        Añadir Producto
                    </button> </router-link>
            </div>
        </div>
        <div class="row">
<!--            display all the products in productbox component-->
            <div v-for="product of products" :key="product.id"
                 class="col-md-6 col-xl-4 col-12 pt-3 d-flex">
                <ProductBox :product="product"/>
            </div>
        </div>
    </div>
</template>
<script>
    import ProductBox from "../../components/ProductBox";
    export default {
        components: {ProductBox},
        props:["products"],
        data() {
            return {
                // Definir isAdmin dentro de data para que sea reactivo
                isAdmin: localStorage.getItem('role') === 'ADMIN',
            };
        },
    }
</script>
