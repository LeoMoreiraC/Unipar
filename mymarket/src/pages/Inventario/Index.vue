<template>
  <q-page class="flex flex-center bg">
    <div>
      <TopBar />
    </div>
    <div>
      <SideBar />
    </div>
    <div class="q-pt-xl q-mt-xl">
      <div class="row q-mb-xl">
        <q-input filled v-model="patrimonio" label="Patrimônio" />
        <q-select
          class="q-ml-xl"
          label="Processador"
          filled
          v-model="processador"
          :options="proc"
          style="width: 250px"
        />
        <q-select
          class="q-ml-xl"
          label="Geração"
          filled
          v-model="geracao"
          :options="ger"
          style="width: 250px"
        />
      </div>
      <div class="row">
        <q-select
          label="Setor"
          filled
          v-model="setor"
          :options="set"
          style="width: 250px"
        />
        <q-input class="q-ml-xl" filled v-model="ram" label="RAM" />
        <q-select
          class="q-ml-xl"
          label="HDD / SSD"
          filled
          v-model="hd"
          :options="hdd"
          style="width: 250px"
        />
        <q-btn
          @click="salvarPatrimonio"
          color="dark"
          label="Cadastrar"
          class="q-ml-xl"
        />
      </div>
      <div class="q-mt-xl">
        <q-table
          :rows="rows"
          :filter="filter"
          :columns="columns"
          dark
          row-key="name"
        >
          <template v-slot:top-right>
            <q-input
              borderless
              dense
              dark
              debounce="300"
              v-model="filter"
              placeholder="Search"
            >
              <template v-slot:append>
                <q-icon name="search" />
              </template>
            </q-input>
          </template>
        </q-table>
      </div>
    </div>
  </q-page>
</template>

<script>
import api from "src/services/api";
import TopBar from "src/components/TopBar/Index.vue";
import SideBar from "src/components/SideBar/Index.vue";
export default {
  name: "MainPage",
  components: {
    TopBar,
    SideBar,
  },
  data() {
    return {
      filter: "",
      ram: "",
      patrimonio: "",
      itens: [],
      rows: [],
      columns: [
        {
          name: "patrimonio",
          label: "Patrimônio",
          align: "left",
          field: (row) => row.patrimonio,
          sortable: true,
        },
        {
          name: "processador",
          label: "Processador",
          align: "left",
          field: (row) => row.processador,
          sortable: true,
        },
        {
          name: "geracao",
          label: "Geração",
          align: "left",
          field: (row) => row.geracao,
          sortable: true,
        },
        {
          name: "setor",
          label: "Setor",
          align: "left",
          field: (row) => row.setor,
          sortable: true,
        },
        {
          name: "ram",
          label: "Ram",
          align: "left",
          field: (row) => row.ram,
          sortable: true,
        },
        {
          name: "hd",
          label: "HD",
          align: "left",
          field: (row) => row.hd,
          sortable: true,
        },
      ],
      hdd: ["HDD", "SSD"],
      proc: [],
      set: [],
      processador: "",
      hd: "",
      geracao: "",
      setor: "",
      ger: [
        "1ª",
        "2ª",
        "3ª",
        "4ª",
        "5ª",
        "6ª",
        "7ª",
        "8ª",
        "9ª",
        "10ª",
        "11ª",
        "12ª",
      ],
      lLista: false,
    };
  },

  mounted() {
    this.carregarTodos();
    this.carregarSetor();
    this.carregarProcessador();
  },
  methods: {
    async salvarPatrimonio() {
      api
        .post("/parqueTecnologico", {
          patrimonio: this.patrimonio,
          processador: this.processador,
          geracao: this.geracao,
          setor: this.setor,
          ram: this.ram,
          hd: this.hd,
        })
        .then(() => {
          alert("Patrimônio salvo");
          this.carregarTodos();
          (this.patrimonio = ""),
            (this.processador = ""),
            (this.geracao = ""),
            (this.setor = ""),
            (this.ram = ""),
            (this.hd = "");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    carregarTodos() {
      this.lLista = true;
      api.get("/parqueTecnologico").then((resp) => {
        console.log(resp);
        this.rows = resp.data;
        this.lLista = false;
      });
    },
    carregarSetor() {
      this.lLista = true;
      api.get("/setor/status").then((resp) => {
        console.log(resp);
        this.set = resp.data.map((item) => item.nome);
        this.lLista = false;
      });
    },
    carregarProcessador() {
      this.lLista = true;
      api.get("/processador").then((resp) => {
        console.log(resp);
        this.proc = resp.data;
        this.lLista = false;
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.q-input {
  width: 250px;
}
.bg {
  background-color: grey;
}
</style>
