const API_BASE_URL = "http://localhost:17071";

async function listarPlataformas() {
  const response = await fetch(`${API_BASE_URL}/plataformas/`);
  if (!response.ok) throw new Error("No se pudieron cargar las plataformas");
  return await response.json();
}

async function listarCursos() {
  const response = await fetch(`${API_BASE_URL}/cursos/`);
  if (!response.ok) throw new Error("No se pudieron listar los cursos");
  return await response.json();
}

async function filtrarCursos(certificado, precioMax) {
  const params = new URLSearchParams();
  if (certificado !== "") params.append("certificado", certificado);
  if (precioMax !== "") params.append("precioMax", precioMax);

  const response = await fetch(`${API_BASE_URL}/cursos/filtrar?${params.toString()}`);
  if (!response.ok) throw new Error("No se pudieron filtrar los cursos");
  return await response.json();
}

async function buscarCursoPorId(id) {
  const response = await fetch(`${API_BASE_URL}/cursos/${id}`);
  if (!response.ok) throw new Error("Curso no encontrado");
  return await response.json();
}

async function buscarCursoPorParametros(nombre, idPlataforma) {
  const params = new URLSearchParams();
  if (nombre) params.append("nombre", nombre);
  if (idPlataforma) params.append("idPlataforma", idPlataforma);

  const response = await fetch(`${API_BASE_URL}/cursos/buscar?${params.toString()}`);
  if (!response.ok) throw new Error("Curso no encontrado");
  return await response.json();
}

async function insertarCurso(curso) {
  const response = await fetch(`${API_BASE_URL}/cursos/`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(curso)
  });

  if (!response.ok) {
    throw new Error(await response.text());
  }

  return await response.json();
}

async function actualizarCurso(id, curso) {
  const response = await fetch(`${API_BASE_URL}/cursos/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(curso)
  });

  if (!response.ok) {
    throw new Error(await response.text());
  }

  return await response.json();
}

async function eliminarCurso(id) {
  const response = await fetch(`${API_BASE_URL}/cursos/${id}`, {
    method: "DELETE"
  });

  if (!response.ok) {
    throw new Error(await response.text());
  }

  return await response.text();
}

//PLATAFORMA
async function insertarPlataforma(plataforma) {
    const response = await fetch(`${API_BASE_URL}/plataformas/`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(plataforma)
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Error al insertar la plataforma");
    }

    return await response.json();
}

async function buscarPlataformaPorId(id) {
    const response = await fetch(`${API_BASE_URL}/plataformas/${id}`);
    if (!response.ok) {
        if (response.status === 404) throw new Error("Plataforma no encontrada");
        throw new Error("Error al buscar la plataforma");
    }
    return await response.json();
}

/*async function buscarPlataformaPorParametros(nombre, url) {
    const params = new URLSearchParams();
    if (nombre) params.append("nombre", nombre);
    if (url) params.append("url", url);

    const response = await fetch(`${API_BASE_URL}/plataformas/buscar?${params.toString()}`);
    if (!response.ok) {
        if (response.status === 404) throw new Error("Plataforma no encontrada con esos parámetros");
        throw new Error("Error en la búsqueda por parámetros");
    }
    return await response.json();
}*/

async function listarPlataformas() {
    const response = await fetch(`${API_BASE_URL}/plataformas/`);
    if (!response.ok) throw new Error("No se pudieron cargar las plataformas");
    return await response.json();
}

async function filtrarPlataformas(nombre) {
    const response = await fetch(`${API_BASE_URL}/plataformas/filtrar?nombre=${nombre}`);
    if (!response.ok) throw new Error("No se pudieron filtrar las plataformas");
    return await response.json();
}

async function actualizarPlataforma(id, plataforma) {
    const response = await fetch(`${API_BASE_URL}/plataformas/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(plataforma)
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Error al actualizar la plataforma");
    }

    return await response.json();
}

async function eliminarPlataforma(id) {
  const response = await fetch(`${API_BASE_URL}/plataformas/${id}`, {
    method: "DELETE"
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || "No se pudo eliminar la plataforma");
  }

  return await response.text();
}
