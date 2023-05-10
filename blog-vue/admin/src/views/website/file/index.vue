<template>
  <div class="file-conatiner">
    <space></space>
  </div>
</template>

<script>
import Space from './Space.vue'

export default {
  name: 'File',

  components: {
    Space
  }
}
</script>

<style lang="scss" scoped>
.file-conatiner {
  padding: 20px;
  height: calc(100vh - 125px);
}
</style>
