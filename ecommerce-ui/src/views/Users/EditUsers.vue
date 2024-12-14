<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3"> Editar Usuario</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form v-if="user">
                    <div class="form-group">
                        <label>Correo</label>
                        <input type="text" class="form-control" v-model="user.email" required/>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" v-model="user.firstName" required/>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" v-model="user.lastName" required/>
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select v-model="user.role" id="role" class="form-control" required>
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-primary"
                            @click="editUsers" >Guardar</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        data() {
            return {
                product: null,
                id: null
            }
        },
        props: ["baseURL", "users"],
        methods: {
            async editUsers() {
                console.log('product', this.product)
                await axios.post(`${this.baseURL}product/update/${this.id}`,
                    this.product)
                    .then(() => {
                        this.$emit("fetchData");
                        this.$router.push({name: 'AdminProduct'})
                        swal({
                            text: "product has been updated successfully",
                            icon: "success"
                        })
                    }).catch(err => console.log('err', err));
            }
        },
        mounted() {
            this.id = this.$route.params.id;
            this.product = this.products.find(product => product.id == this.id)
        }
    }
</script>
