<template>
  <div>
    <h1>个人信息</h1><br>
    用户名：{{this.$cookies.get("name")}}
  </div>
</template>

<script>
  export default {
    name: 'UserProfile',
  }
</script>

<style scoped>

</style>
