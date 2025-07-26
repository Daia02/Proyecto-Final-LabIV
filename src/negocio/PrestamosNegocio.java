package negocio;

import java.math.BigDecimal;
import java.util.List;

import Expeciones.MaximoPrestamos;
import Inegocio.INegocioPrestamos;

import dao.PrestamosDao;
import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.SolicitudPrestamo;
import entidades.TipoCuenta;
import entidades.Usuario;

public class PrestamosNegocio implements INegocioPrestamos {
dao.PrestamosDao dao= new PrestamosDao();
dao.UsuarioDao daoUsuario=new UsuarioDao();
public boolean existeCbu(String cbu) {
	
	return dao.ExisteCbu(cbu);
}



    @Override
    public boolean agregarPrestamo(BigDecimal importe, int coutas, String usuario, int tipoCuenta, BigDecimal total, BigDecimal interes) throws MaximoPrestamos {
        Cliente us = daoUsuario.devolverInformacionUsuario(usuario);
        List<Cuenta> buscar = dao.ObtenerTipoCuentaUsuario(usuario);
        Prestamo prestamo = new Prestamo();

        for (Cuenta d : buscar) {
            if (d.getTipoCuenta().getNroTipoCuenta() == tipoCuenta) {
                prestamo.setnumeroCuenta(d.getNroCuenta());
            }
        }

        System.out.println(prestamo.getnumeroCuenta());
        prestamo.setCoutas(coutas);
        prestamo.setImporte(importe);
        prestamo.setTotal(total);
        prestamo.setInteres(interes);
        prestamo.setCliente(us);

        if (!dao.AgregarPrestamos(prestamo)) {
            throw new MaximoPrestamos("Se ha alcanzado el máximo de préstamos permitidos.");
        }

        return true;
    }



@Override
public List<Cuenta> ObtenerTipoCuentaUsuario(String usuario) {

	
	return dao.ObtenerTipoCuentaUsuario(usuario);
}



@Override
public List<SolicitudPrestamo> ObtenerClienteEstadoPrestamo(String usuario) {
	
	return dao.ObtenerClienteEstadoPrestamo(usuario);
}





}
