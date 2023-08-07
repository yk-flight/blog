<template>
  <v-app>
    <NavBar></NavBar>
    <v-main>
      <router-view/>
    </v-main>
    <Footer></Footer>
  </v-app>
</template>

<script>
import NavBar from './layout/NavBar'
import Footer from './layout/Footer'

export default {
  name: 'App',

  components: { NavBar, Footer }
}
</script>
