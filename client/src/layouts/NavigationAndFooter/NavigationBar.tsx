function NavigationBar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark main-color py-3">
            <div className="container-fluid">
                <span className="navbar-brand">Golden Group</span>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-toggler" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link" href="/">
                                Домашняя
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/">
                                Показатели производства
                            </a>
                        </li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item m-1">
                            <a type="button" className="btn btn-outline-light" href="/">
                                Вход
                            </a>
                        </li>
                    </ul>
                </div>
            </div >
        </nav >
    );
}

export default NavigationBar;