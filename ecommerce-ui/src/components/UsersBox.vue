<template>
    <div class="card w-100 h-100" style="margin-top: 10px">
      <div class="embed-responsive embed-responsive-16by9">
        <img
          class="card-img-top embed-responsive-item"
          :src="category.imageUrl"
          alt="Card image cap"
        />
      </div>
      <div class="card-body">
        <router-link :to="{name: 'ListUsers', params: {id: category.id} }">
          <h5 class="card-title">{{ category.categoryName }}</h5>
        </router-link>
        <p class="card-text">
          {{ category.description }}
        </p>
        <router-link :to="{name: 'EditUsers', params: {id: user.id}}"
                     v-show="$route.name == 'AdminUsers'">
          <button class="btn btn-primary">Editar</button>
        </router-link>
      </div>
    </div>
  </template>
  <script>
  export default {
    name: "UsersBox",
    props: ["users", "isAdmin"],
    methods: {},
  };
  </script>
  
  <style scoped>
  .card-img-top {
    object-fit: cover;
  }
  </style>
  