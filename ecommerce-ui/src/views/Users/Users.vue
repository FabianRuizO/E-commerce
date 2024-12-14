<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h3 class="pt-3">Users</h3>
          <router-link v-if="isAdmin" :to="{ name: 'AddUsers' }">
            <button class="btn" style="float:right">Add Users</button>
          </router-link>
        </div>
      </div>
      <div class="row">
        <div
          v-for="user of users"
          :key="user.id"
          class="col-xl-4 col-md-6 col-12 pt-3 d-flex"
        >
          <UsersBox :users="users"> </UsersBox>
        </div>
      </div>
    </div>
  </template>
  <script>
  import UsersBox from "../../components/UsersBox.vue";
  export default {
    name: "Users",
    props:["users"],
    components: { UsersBox },
    data() {
      return {
        baseURL: "http://localhost:8080/",
        isAdmin: localStorage.getItem('role') === 'ADMIN',
      };
    },
    mounted(){
      if (this.$route.name=='AdminUsers' && !localStorage.getItem('token')) {
        this.$router.push({name : 'Signin'});
      }
    },
  
    methods: {
    },
  };
  </script>
  <style scoped></style>
  