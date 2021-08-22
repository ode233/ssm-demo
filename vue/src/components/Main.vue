<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu class="el-menu-vertical-demo"
                 default-active="1-1">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-caret-right"></i>用户管理</template>
            <el-menu-item-group>
              <el-menu-item index="1-1">
                <!--name:传组件名， params：传递参数， 需要对象： v-bind-->
                <router-link :to="{name:'UserProfile'}">个人信息</router-link>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-caret-right"></i>文件管理</template>
            <el-menu-item-group>
              <el-menu-item index="2-1">
                <router-link :to="{name:'DocList'}">文件列表</router-link>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting el-dropdown-link"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="gotoProfile">个人信息</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>{{this.$cookies.get("name")}}</span>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Main",
    methods: {
      logout() {
        this.$cookies.remove("name")
        this.$router.push("/login");
      },
      gotoProfile(){
        this.$router.push("/user/profile/");
      }
    }
  }
</script>

<style scoped lang="scss">
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }

  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
