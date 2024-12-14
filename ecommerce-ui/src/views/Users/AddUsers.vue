<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4>Add User</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form>
                    <div class="form-group">
                        <label for="Email">Email</label>
                        <input type="email" v-model="email" class="form-control" required/>
                    </div>

                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" v-model="firstName" class="form-control" required/>
                    </div>
                
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text"  v-model="lastName" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select v-model="role" id="role" class="form-control" required>
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="Password" v-model="password" class="form-control" required />
                    </div>
                    <button type="button" class="btn btn-primary" @click="addUser">Add User</button>
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
        props: ["baseURL", "users"],
        data() {
            return {
                id: null,
                email: null,
                firstName: null,
                lastName: null,
                password: null,
                role: null
            }
        },
        methods: {
            addUser() {
                const newUser = {
                    id: this.id,
                    email: this.email,
                    firstName: this.firstName,
                    lastName: this.lastName,
                    password: this.password,
                    role: this.role
                };

                axios.post(this.baseURL+"user/signup", newUser)
                .then(() => {
                    this.$router.push({name: 'AdminUsers'});
                    swal({
                        text: "User added",
                        icon: "success"
                    })
                }).catch((err)=> {
                    console.log("err", err);
                })


            }
        }
    }
</script>
