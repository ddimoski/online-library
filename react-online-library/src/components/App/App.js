import logo from '../../logo.svg';
import './App.css';


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            selectedBook: {},
            authors: [],
            countries: [],
            categories: []
        }
    }

    render() {
        return (
          <Router>
              <Header/>
              <main>
                  <div className="container">
                      <Route path={"/books/add"} exact render={() =>
                        <BookCreate book={this.state.manufacturers}/>}/>
                      <Route path={"/books"} exact render={() =>
                        <Categories categories={this.state.categories}/>}/>
                      <Route path={"/products/add"} exact render={() =>
                        <ProductAdd categories={this.state.categories}
                                    manufacturers={this.state.manufacturers}
                                    onAddProduct={this.addProduct}/>}/>
                      <Route path={"/products/edit/:id"} exact render={() =>
                        <ProductEdit categories={this.state.categories}
                                     manufacturers={this.state.manufacturers}
                                     onEditProduct={this.editProduct}
                                     product={this.state.selectedProduct}/>}/>
                      <Route path={"/products"} exact render={() =>
                        <Products products={this.state.products}
                                  onDelete={this.deleteProduct}
                                  onEdit={this.getProduct}/>}/>
                      <Redirect to={"/products"}/>
                  </div>
              </main>
          </Router>
        );
    }

}

export default App;
