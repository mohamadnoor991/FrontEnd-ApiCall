<template>
  <div id="app">
    <header>
      <div class="title"><h1>{{title}}</h1></div>
    </header>
    
    <section >
      <div class="container">
        <div class="row justify-content-center">
        <div class="col-sm-4 ">
        <div class="sticky-top">
            <create-new></create-new>
            </div>
          </div>
          <div class="col-sm-8">
            <main-navigation></main-navigation>
            <windows-list></windows-list>
            <rooms-list></rooms-list>
          </div>      
        </div>
      </div>
    </section>
     
  </div>
</template>

<script>
import CreateNew from './components/CreateNew.vue';
import MainNavigation from './components/MainNavigation.vue';
import RoomsList from './components/RoomsList.vue';
import WindowsList from './components/WindowsList.vue';

export default {
  name: 'App',
  components: {
    MainNavigation,
    WindowsList,
    RoomsList,
    CreateNew
  },
  data: function() {
    return {
      title: 'My Vue App For Group 12[Mohamad-Jehad-Reduit]'
    }
  }
}
</script>

<style lang="scss" scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
