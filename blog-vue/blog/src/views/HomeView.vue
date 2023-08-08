<template>
  <div>
    <banner />
    <wave />
    <hello-world />
  </div>
</template>

<script>
import Banner from '../components/Banner/index.vue'
import Wave from '../components/Wave/index.vue'
import HelloWorld from '../components/HelloWorld'

export default {
  name: 'HomeView',

  components: {
    HelloWorld,
    Banner,
    Wave
  }
}
</script>
