<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/icon?family=Material+Icons"
  />
  <q-dialog v-model="pesquisaPatrimonio">
    <q-card
      class="top"
      style="width: fit-content; max-width: fit-content; left: 7%"
    >
      <q-card-section class="q-pa-sm">
        <q-item-label header class="top full-width q-mb-sm">
          Pesquisa de Patrimônio
        </q-item-label>
        <q-table
          :rows="rowsPesquisarPatrimonio"
          :filter="filter"
          :columns="columnsPesquisarPatrimonio"
          dark
          key="id"
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
            <q-btn class="q-ml-sm" @click="novoPatrimonio = true"
              >Novo Patrimônio</q-btn
            >
          </template>
          <template v-slot:body-cell-action="props">
            <q-td :props="props">
              <q-btn
                icon="visibility"
                color="green"
                dense
                @click="openViewPatrimonio(props.row)"
              />
              <q-dialog v-model="abrirPatrimonio">
                <q-card class="top">
                  <q-card-section class="row items-center">
                    <!-- abrir manutenções -->
                    <q-card-actions align="center" class="col">
                    </q-card-actions>
                  </q-card-section>
                </q-card>
              </q-dialog>
              <!-- edita patrimônio -->
              <q-btn
                icon="create"
                color="primary"
                class="q-ml-sm"
                dense
                @click="openEditPatrimonio(props.row)"
              />
              <q-dialog v-model="confirmaEditarPatrimonio">
                <q-card class="top" style="left: 7%">
                  <q-card-section class="col items-center">
                    <q-input
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.patrimonio"
                      :disable="true"
                      label="Patrimonio"
                    />
                    <q-select
                      class="q-mt-sm"
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.processador"
                      :options="newProcessadorList"
                      label="Processador"
                    />
                    <q-select
                      class="q-mt-sm"
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.geracao"
                      :options="newGeracaoList"
                      label="Geração"
                    />
                    <q-select
                      class="q-mt-sm"
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.setor"
                      :options="newSetorList"
                      label="Setor"
                    />
                    <q-input
                      class="q-mt-sm"
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.ram"
                      label="Memória Ram"
                    />
                    <q-select
                      class="q-mt-sm"
                      filled
                      required
                      dark
                      v-model="editedRowPatrimonio.hd"
                      :options="newHdList"
                      label="HD"
                    />
                    <q-card-actions align="center" class="col">
                      <q-btn
                        class="row"
                        flat
                        label="Salvar"
                        @click="salvarEdicaoPatrimonio(props.row)"
                        color="green"
                        v-close-popup
                      />
                    </q-card-actions>
                  </q-card-section>
                </q-card>
              </q-dialog>
              <!-- deleta patrimonio -->
              <q-btn
                icon="delete"
                color="negative"
                dense
                class="q-ml-sm"
                @click="openDeletePatrimonio(props.row)"
              />
              <q-dialog v-model="confirmaDeletarPatrimonio" persistent>
                <q-card class="top">
                  <q-card-section class="row items-center">
                    <span class="q-ml-sm"
                      >Confirma a exclusão deste Patrimônio ?</span
                    >
                  </q-card-section>

                  <q-card-actions align="center">
                    <q-btn
                      flat
                      label="Não"
                      color="primary"
                      @click="cancelaDeletarPatrimonio"
                      v-close-popup
                    />
                    <q-btn
                      flat
                      label="Sim"
                      @click="deletaPatrimonio(props.row)"
                      color="primary"
                      v-close-popup
                    />
                  </q-card-actions>
                </q-card>
              </q-dialog>
            </q-td>
          </template>
        </q-table>
      </q-card-section>
    </q-card>
  </q-dialog>
  <q-dialog v-model="novoPatrimonio" @hide="limparInputs">
    <q-card class="top" style="left: 7%">
      <q-card-section class="col items-center">
        <q-input
          filled
          required
          dark
          v-model="newPatrimonio"
          label="Patrimonio"
        />
        <q-select
          class="q-mt-sm"
          filled
          required
          dark
          v-model="newProcessador"
          :options="newProcessadorList"
          label="Processador"
        />
        <q-select
          class="q-mt-sm"
          filled
          required
          dark
          v-model="newGeracao"
          :options="newGeracaoList"
          label="Geração"
        />
        <q-select
          class="q-mt-sm"
          filled
          required
          dark
          v-model="newSetor"
          :options="newSetorList"
          label="Setor"
        />
        <q-input
          class="q-mt-sm"
          filled
          required
          dark
          v-model="newRam"
          label="Memória Ram"
        />
        <q-select
          class="q-mt-sm"
          filled
          required
          dark
          v-model="newHd"
          :options="newHdList"
          label="HD"
        />
      </q-card-section>
      <q-card-actions>
        <q-btn
          class="botao"
          @click="salvarPatrimonio"
          filled
          :disable="!camposPreenchidos"
          label="Salvar"
          color="dark"
          v-close-popup
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
  <q-page class="flex bg">
    <div>
      <TopBar />
    </div>
    <div>
      <SideBar />
    </div>
    <div class="q-ml-sm q-mt-xl q-pt-xl col">
      <q-btn @click="pesquisaPatrimonio = true" class="row botao" color="dark">
        Patrimônio</q-btn
      >

      <q-btn @click="relatorio = true" class="q-mt-xl row botao" color="dark">
        Relatórios</q-btn
      >
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
      editedRowPatrimonio: "",
      confirmaEditarPatrimonio: false,
      newPatrimonio: "",
      newProcessadorList: [],
      newHdList: ["HDD", "SSD"],
      newSetorList: [],
      newGeracaoList: [
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
      newProcessador: "",
      newHd: "",
      newSetor: "",
      newGeracao: "",
      newRam: "",
      cadastrarPatrimonio: "",
      novoPatrimonio: false,
      pesquisaPatrimonio: false,
      filter: "",
      itens: [],
      rowsPesquisarPatrimonio: [],
      columnsPesquisarPatrimonio: [
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

        {
          name: "action",
          label: "Ações",
          align: "center",
        },
      ],
      lLista: false,
    };
  },
  computed: {
    camposPreenchidos() {
      // Verifique se todos os campos estão preenchidos
      return (
        this.newPatrimonio &&
        this.newProcessador &&
        this.newGeracao &&
        this.newSetor &&
        this.newRam &&
        this.newHd
      );
    },
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
          patrimonio: this.newPatrimonio,
          processador: this.newProcessador,
          geracao: this.newGeracao,
          setor: this.newSetor,
          ram: this.newRam,
          hd: this.newHd,
        })
        .then(() => {
          alert("Patrimônio salvo");
          this.carregarTodos();
          (this.newPatrimonio = ""),
            (this.newProcessador = ""),
            (this.newGeracao = ""),
            (this.newSetor = ""),
            (this.newRam = ""),
            (this.newHd = "");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    carregarTodos() {
      this.lLista = true;
      api.get("/parqueTecnologico").then((resp) => {
        console.log(resp);
        this.rowsPesquisarPatrimonio = resp.data;
        this.lLista = false;
      });
    },
    carregarSetor() {
      this.lLista = true;
      api.get("/setor/status").then((resp) => {
        console.log(resp);
        this.newSetorList = resp.data.map((item) => item.nome);
        this.lLista = false;
      });
    },
    carregarProcessador() {
      this.lLista = true;
      api.get("/processador/status").then((resp) => {
        console.log(resp);
        this.newProcessadorList = resp.data.map((item2) => item2.nome);
        this.lLista = false;
      });
    },
    openViewPatrimonio(row) {
      this.verRowPatrimonio = { ...row };
      this.abrirPatrimonio = true;
    },
    limparInputs() {
      this.newPatrimonio = "";
      this.newProcessador = "";
      this.newGeracao = "";
      this.newSetor = "";
      this.newRam = "";
      this.newHd = "";
    },
    openEditPatrimonio(row) {
      this.editedRowPatrimonio = { ...row };
      this.confirmaEditarPatrimonio = true;
    },
    salvarEdicaoPatrimonio() {
      api
        .post("/parqueTecnologico", {
          id: this.editedRowPatrimonio.id,
          patrimonio: this.editedRowPatrimonio.patrimonio,
          processador: this.editedRowPatrimonio.processador,
          geracao: this.editedRowPatrimonio.geracao,
          setor: this.editedRowPatrimonio.setor,
          ram: this.editedRowPatrimonio.ram,
          hd: this.editedRowPatrimonio.hd,
        })

        .then(() => {
          this.carregarTodos();
        });
    },
  },
};
</script>
<style lang="scss">
.q-input {
  width: 250px;
}
.bg {
  background-color: grey;
}
.botao {
  width: 250px;
  height: 40px;
}
.top {
  background-color: $dark;
  text-align: center;
  color: white;
}
.q-dialog__inner {
  width: 88vw !important;
}
</style>
