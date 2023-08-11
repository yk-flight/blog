<template>
  <div>
    <banner />
    <hello-world />
  </div>
</template>

<script>
import Banner from '../../components/Banner/index.vue'
import HelloWorld from '../../components/HelloWorld.vue'

export default {
  name: 'HomeView',

  components: {
    HelloWorld,
    Banner
  }
}
</script>
