<template>
  <q-page class="flex bg">
    <div>
      <TopBar />
    </div>
    <div>
      <SideBar />
    </div>
    <div class="q-ml-sm q-mt-xl q-pt-xl col">
      <q-btn @click="editarSetor = true" class="row botao" color="dark">
        Setor</q-btn
      >

      <q-dialog v-model="editarSetor">
        <q-card class="top">
          <q-card-section class="q-pa-sm">
            <q-item-label header class="top full-width q-mb-sm">
              Cadastro de Setor
            </q-item-label>
            <q-table
              :rows="rows"
              :filter="filter"
              :columns="columns"
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

                <q-btn class="q-ml-sm" @click="novoSetor = true"
                  >Novo Setor</q-btn
                >
              </template>
              <template v-slot:body-cell-action="props">
                <q-td :props="props">
                  <q-btn
                    icon="create"
                    color="primary"
                    dense
                    @click="openEditDialog(props.row)"
                  />
                  <q-dialog v-model="confirmaEditarSetor">
                    <q-card class="top">
                      <q-card-section class="row items-center">
                        <q-input
                          class="q-mr-sm"
                          v-model="editedRow.id"
                          filled
                          label="ID"
                          dark
                          style="width: 50px"
                          :disable="true"
                        ></q-input>
                        <q-input
                          class="q-mr-sm"
                          v-model="editedRow.nome"
                          filled
                          label="Setor"
                          dark
                          style="width: 250px"
                        ></q-input>
                        <q-select
                          class="q-mr-sm"
                          v-model="editedRow.status"
                          filled
                          label="Status"
                          :options="status"
                          dark
                          style="width: 150px"
                        ></q-select>
                        <q-card-actions align="center" class="col">
                          <q-btn
                            class="row"
                            flat
                            label="Salvar"
                            @click="salvarEdicao(props.row)"
                            color="green"
                            v-close-popup
                          />
                        </q-card-actions>
                      </q-card-section>
                    </q-card>
                  </q-dialog>

                  <q-btn
                    icon="delete"
                    color="negative"
                    dense
                    class="q-ml-sm"
                    @click="openDeleteDialog(props.row)"
                  />
                  <q-dialog v-model="confirmaDeletarSetor" persistent>
                    <q-card class="top">
                      <q-card-section class="row items-center">
                        <span class="q-ml-sm"
                          >Confirma a exclusão deste Setor ?</span
                        >
                      </q-card-section>

                      <q-card-actions align="center">
                        <q-btn
                          flat
                          label="Não"
                          color="primary"
                          @click="cancelaDeletarSetor"
                          v-close-popup
                        />
                        <q-btn
                          flat
                          label="Sim"
                          @click="deletaSetor(props.row)"
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
      <q-dialog v-model="novoSetor">
        <q-card class="top">
          <q-card-section>
            <q-input
              filled
              required
              dark
              v-model="cadastrarSetor"
              label="Novo Setor"
            />
          </q-card-section>
          <q-card-actions>
            <q-btn
              class="botao"
              @click="salvarSetor"
              filled
              :disable="!cadastrarSetor.trim()"
              label="Salvar"
              color="dark"
              v-close-popup
            />
          </q-card-actions>
        </q-card>
      </q-dialog>
      <q-btn
        class="q-mt-xl row botao"
        @click="editarProcessador = true"
        color="dark"
      >
        Processador</q-btn
      >
      <!--  -->
      <q-dialog v-model="editarProcessador">
        <q-card class="top">
          <q-card-section class="q-pa-sm">
            <q-item-label header class="top full-width q-mb-sm">
              Cadastro de Processador
            </q-item-label>
            <q-table
              :rows2="rows2"
              :filter="filter"
              :columns2="columns2"
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

                <q-btn class="q-ml-sm" @click="novoProcessador = true"
                  >Novo Processador</q-btn
                >
              </template>
              <template v-slot:body-cell-action="props">
                <q-td :props="props">
                  <q-btn
                    icon="create"
                    color="primary"
                    dense
                    @click="openEditProcessador(props.row2)"
                  />
                  <q-dialog v-model="confirmaEditarProcessador">
                    <q-card class="top">
                      <q-card-section class="row items-center">
                        <q-input
                          class="q-mr-sm"
                          v-model="editedRowProcessador.id"
                          filled
                          label="ID"
                          dark
                          style="width: 50px"
                          :disable="true"
                        ></q-input>
                        <q-input
                          class="q-mr-sm"
                          v-model="editedRowProcessador.nome"
                          filled
                          label="Processador"
                          dark
                          style="width: 250px"
                        ></q-input>
                        <q-select
                          class="q-mr-sm"
                          v-model="editedRowProcessador.status"
                          filled
                          label="Status"
                          :options="status"
                          dark
                          style="width: 150px"
                        ></q-select>
                        <q-card-actions align="center" class="col">
                          <q-btn
                            class="row"
                            flat
                            label="Salvar"
                            @click="salvarEdicaoProcessador(props.row2)"
                            color="green"
                            v-close-popup
                          />
                        </q-card-actions>
                      </q-card-section>
                    </q-card>
                  </q-dialog>

                  <q-btn
                    icon="delete"
                    color="negative"
                    dense
                    class="q-ml-sm"
                    @click="openDeleteDialog(props.row2)"
                  />
                  <q-dialog v-model="confirmaDeletarProcessador" persistent>
                    <q-card class="top">
                      <q-card-section class="row items-center">
                        <span class="q-ml-sm"
                          >Confirma a exclusão deste Processador ?</span
                        >
                      </q-card-section>

                      <q-card-actions align="center">
                        <q-btn
                          flat
                          label="Não"
                          color="primary"
                          @click="cancelaDeletarProcessador"
                          v-close-popup
                        />
                        <q-btn
                          flat
                          label="Sim"
                          @click="deletaProcessador(props.row2)"
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
      <q-dialog v-model="novoProcessador">
        <q-card class="top">
          <q-card-section>
            <q-input
              filled
              required
              dark
              v-model="cadastrarProcessador"
              label="Novo Setor"
            />
          </q-card-section>
          <q-card-actions>
            <q-btn
              class="botao"
              @click="salvarProcessador"
              filled
              :disable="!cadastrarProcessador.trim()"
              label="Salvar"
              color="dark"
              v-close-popup
            />
          </q-card-actions>
        </q-card>
      </q-dialog>
      <q-btn class="q-mt-xl row botao" color="dark"> Usuarios</q-btn>
    </div>
  </q-page>
</template>

<script>
import api from "src/services/api";
import TopBar from "src/components/TopBar/Index.vue";
import SideBar from "src/components/SideBar/Index.vue";
import { useQuasar } from "quasar";
export default {
  name: "MainPage",
  components: {
    TopBar,
    SideBar,
  },
  data() {
    const $q = useQuasar();
    return {
      deletedRow: null,
      deletedRow2: null,
      status: ["ATIVO", "INATIVO"],
      filter: "",
      confirmaEditarSetor: false,
      confirmaDeletarSetor: false,
      cadastrarSetor: "",
      confirmaEditarProcessador: false,
      confirmaDeletarProcessador: false,
      cadastrarProcessador: "",
      rows: [],
      columns: [
        {
          name: "id",
          label: "ID",
          align: "left",
          field: (row) => row.id,
          sortable: true,
        },
        {
          name: "nome",
          label: "Setor",
          align: "left",
          field: (row) => row.nome,
          sortable: true,
        },
        {
          name: "status",
          label: "Status",
          align: "left",
          field: (row) => row.status,
          sortable: true,
        },

        {
          name: "action",
          label: "Ações",
          align: "center",
        },
      ],
      rows2: [],
      columns2: [
        {
          name: "id",
          label: "ID",
          align: "left",
          field: (row2) => row2.id,
          sortable: true,
        },
        {
          name: "nome",
          label: "Setor",
          align: "left",
          field: (row2) => row2.nome,
          sortable: true,
        },
        {
          name: "status",
          label: "Status",
          align: "left",
          field: (row2) => row2.status,
          sortable: true,
        },

        {
          name: "action",
          label: "Ações",
          align: "center",
        },
      ],
      openSetor: false,
      editarSetor: false,
      novoSetor: false,
      openProcessador: false,
      editarProcessador: false,
      novoProcessador: false,
      editedRow: { id: null, nome: "", status: "" },
    };
  },
  mounted() {
    this.carregarTodos();
    this.carregarTodosProcessador();
  },
  methods: {
    async salvarSetor(triggerPositive) {
      if (this.cadastrarSetor.trim() !== "") {
        api
          .post("/setor", {
            nome: this.cadastrarSetor,
            status: "ATIVO",
          })
          .then(() => {
            this.carregarTodos();
            this.cadastrarSetor = "";
          });
      } else {
        console.error("O campo não pode estar vazio !");
      }
    },
    async salvarProcessador(triggerPositive) {
      if (this.cadastrarProcessador.trim() !== "") {
        api
          .post("/processador", {
            nome: this.cadastrarProcessador,
            status: "ATIVO",
          })
          .then(() => {
            this.carregarTodosProcessador();
            this.cadastrarProcessador = "";
          });
      } else {
        console.error("O campo não pode estar vazio !");
      }
    },
    carregarTodos() {
      this.lLista = true;
      api.get("/setor").then((resp) => {
        console.log(resp);
        this.rows = resp.data;
        this.lLista = false;
      });
    },
    carregarTodosProcessador() {
      this.lLista = true;
      api.get("/processador").then((resp) => {
        console.log(resp);
        this.rows2 = resp.data;
        this.lLista = false;
      });
    },

    openDeleteDialog(row) {
      this.deletedRow = { ...row };
      this.confirmaDeletarSetor = true;
    },
    cancelaDeletarSetor() {
      this.deletedRow = null;
      this.confirmaDeletarSetor = false;
    },
    deletaSetor(row) {
      console.log(this.deletedRow.id);

      api.delete(`/setor/${this.deletedRow.id}`).then(() => {
        this.carregarTodos();
      });
      const index = this.rows.findIndex(
        (item) => item.id === this.deletedRow.id
      );

      // Remover a linha da tabela
      if (index !== -1) {
        this.rows.splice(index, 1);
      }
      // Limpar a variável deletedRow
      this.deletedRow = null;

      // Fechar o diálogo
      this.confirmaDeletarSetor = false;
    },
    salvarEdicao(row) {
      api
        .post("/setor", {
          id: this.editedRow.id,
          nome: this.editedRow.nome,
          status: this.editedRow.status,
        })
        .then(() => {
          this.carregarTodos();
        });
    },
    openEditDialog(row) {
      this.editedRow = { ...row };
      this.confirmaEditarSetor = true;
    },

    openDeleteDialogProcessador(row2) {
      this.deletedRow2 = { ...row2 };
      this.confirmaDeletarProcessador = true;
    },
    cancelaDeletarProcessador() {
      this.deletedRow2 = null;
      this.confirmaDeletarProcessador = false;
    },
    deletaProcessador(row2) {
      console.log(this.deletedRow2.id);

      api.delete(`/processador/${this.deletedRow2.id}`).then(() => {
        this.carregarTodosProcessador();
      });
      const index = this.rows2.findIndex(
        (item) => item.id === this.deletedRow2.id
      );

      // Remover a linha da tabela
      if (index !== -1) {
        this.rows2.splice(index, 1);
      }
      // Limpar a variável deletedRow
      this.deletedRow2 = null;

      // Fechar o diálogo
      this.confirmaDeletarProcessador = false;
    },
    salvarEdicaoProcessador(row) {
      api
        .post("/processador", {
          id: this.editedRow2.id,
          nome: this.editedRow2.nome,
          status: this.editedRow2.status,
        })
        .then(() => {
          this.carregarTodosProcessador();
        });
    },
    openEditDialogProcessador(row2) {
      this.editedRow2 = { ...row2 };
      this.confirmaEditarProcessador = true;
    },
  },
};
</script>
<style lang="scss" scoped>
.botao {
  width: 250px;
  height: 40px;
}
.top {
  background-color: $dark;
  text-align: center;
  color: white;
}
.bg {
  background-color: grey;
}
</style>
