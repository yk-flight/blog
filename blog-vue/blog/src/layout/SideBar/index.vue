<template>
  <div class="SideBar">
    <Phone></Phone>
  </div>
</template>

<script>
import Phone from './components/Phone.vue'

export default {
  name: 'SideBar',

  components: { Phone },

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.SideBar {
  position: sticky;
  width: 100%;
  top: calc(1.5rem + 56px);
  z-index: 1;
}
</style>
