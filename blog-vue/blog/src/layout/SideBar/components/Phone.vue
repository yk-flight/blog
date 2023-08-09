<template>
  <div class="phone-container">
    <div class="phone-header">
      <span style="width: 25%;">00:31</span>
      <div class="island"></div>
      <div class="icon">
        <svg-icon icon="signal"></svg-icon>
        <svg-icon icon="wifi"></svg-icon>
        <svg-icon icon="battery"></svg-icon>
      </div>
    </div>

    <div class="phone-footer">
      <div class="home"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogPhone',

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
.phone-container {
  background: url('../../../assets/images/iPhone-background.jpg') center center/ cover no-repeat;
  border: 9px solid #000;
  height: 33.625rem;
  width: 18.75rem;
  border-radius: 36px;
  box-shadow: 0 0 2px #000;
  transition: box-shadow 0.3s ease;
}
.phone-container:hover {
  box-shadow: 1px 2px 10px #000;
}
.phone-header {
  width: 100%;
  height: 1.5rem;
  margin-top: 8px;
  padding: 0 0.625rem;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.island {
  width: 45%;
  height: 1.25rem;
  background-color: #000;
  border-radius: 10px;
}
.icon {
  width: 25%;
  .svg-icon {
    margin-left: 4px;
  }
}
.phone-footer {
  // position: relative;
  margin-top: 29.625rem;
  display: flex;
  justify-content: center;
  width: 100%;

  .home {
    height: 5px;
    width: 120px;
    border-radius: 3px;
    background-color: #fff;
  }
}
</style>
