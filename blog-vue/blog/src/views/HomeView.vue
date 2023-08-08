<template>
  <div>
    <banner></banner>
    <hello-world />
  </div>
</template>

<script>
import Banner from '../components/Banner/index.vue'
import HelloWorld from '../components/HelloWorld'

export default {
  name: 'HomeView',

  components: {
    HelloWorld,
    Banner
  }
}
</script>
