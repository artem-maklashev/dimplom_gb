function MainTable() {
    return (
        <div className="table-responsive">
            <table className="table table-fluide table-striped table-bordered table-hover">
                <span className="border border-warning">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Фамилия</th>
                            <th scope="col">Обращение</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry the Bird</td>
                            <td ></td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
                </span>
            </table>
        </div>
    );
}
export default MainTable;