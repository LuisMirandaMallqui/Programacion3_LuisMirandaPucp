using Newtonsoft.Json;
using System;
using System.Text;
using System.Text.Json.Serialization;
using JsonIgnoreAttribute = System.Text.Json.Serialization.JsonIgnoreAttribute;

namespace PUCP.TransitSoft.Modelo {
    public class Infraccion {
        [JsonIgnore]
        public int Id { get; set; }

        [JsonPropertyName("placa")]
        public string Placa { get; set; }

        [JsonPropertyName("velocidad")]
        public double Velocidad { get; set; }

        [JsonPropertyName("limite")]
        public double Limite { get; set; }

        [JsonPropertyName("exceso")]
        public double Exceso { get; set; }

        [JsonPropertyName("marcaVehiculo")]
        public string MarcaVehiculo { get; set; }

        [JsonPropertyName("modeloVehiculo")]
        public string ModeloVehiculo { get; set; }

        [JsonPropertyName("anhoVehiculo")]
        public int AnhoVehiculo { get; set; }

        [JsonPropertyName("dniPropietario")]
        public string DniPropietario { get; set; }

        [JsonPropertyName("nombresPropietario")]
        public string NombresPropietario { get; set; }

        [JsonPropertyName("apellidosPropietario")]
        public string ApellidosPropietario { get; set; }

        [JsonPropertyName("direccionPropietario")]
        public string DireccionPropietario { get; set; }

        [JsonPropertyName("modeloCamara")]
        public string ModeloCamara { get; set; }

        [JsonPropertyName("codigoSerieCamara")]
        public string CodigoSerieCamara { get; set; }

        [JsonPropertyName("latitud")]
        public long Latitud { get; set; }

        [JsonPropertyName("longitud")]
        public long Longitud { get; set; }

        [JsonPropertyName("monto")]
        public double Monto { get; set; }

        [JsonPropertyName("fechaCaptura")]
        public long FechaCapturaTimestamp { get; set; }

        [JsonPropertyName("fechaRegistro")]
        public long FechaRegistroTimestamp { get; set; }

        public DateTime FechaCaptura {
            get {
                return DateTimeOffset.FromUnixTimeMilliseconds(FechaCapturaTimestamp).LocalDateTime;
            }
        }

        public DateTime FechaRegistro {
            get {
                return DateTimeOffset.FromUnixTimeMilliseconds(FechaRegistroTimestamp).LocalDateTime;
            }
        }

        public override string ToString() {
            var sb = new StringBuilder();
            sb.AppendLine($"ID: {this.Id}");
            sb.AppendLine($"{"Placa:",-25}{this.Placa}");
            sb.AppendLine($"{"Velocidad:",-25}{this.Velocidad}");
            sb.AppendLine($"{"Límite:",-25}{this.Limite}");
            sb.AppendLine($"{"Exceso:",-25}{this.Exceso}");
            sb.AppendLine($"{"Marca del Vehículo:",-25}{this.MarcaVehiculo}");
            sb.AppendLine($"{"Modelo del Vehículo:",-25}{this.ModeloVehiculo}");
            sb.AppendLine($"{"Año del Vehículo:",-25}{this.AnhoVehiculo}");
            sb.AppendLine($"{"DNI del Propietario:",-25}{this.DniPropietario}");
            sb.AppendLine($"{"Nombres del Propietario:",-25}{this.NombresPropietario}");
            sb.AppendLine($"{"Apellidos del Propietario:",-25}{this.ApellidosPropietario}");
            sb.AppendLine($"{"Dirección del Propietario:",-25}{this.DireccionPropietario}");
            sb.AppendLine($"{"Modelo de la Cámara:",-25}{this.ModeloCamara}");
            sb.AppendLine($"{"Código de Serie Cámara:",-25}{this.CodigoSerieCamara}");
            sb.AppendLine($"{"Latitud:",-25}{this.Latitud}");
            sb.AppendLine($"{"Longitud:",-25}{this.Longitud}");
            sb.AppendLine($"{"Monto:",-25}{this.Monto}");
            sb.AppendLine($"{"Fecha de Captura:",-25}{this.FechaCaptura:yyyy-MM-dd HH:mm:ss}");
            sb.AppendLine($"{"Fecha de Registro:",-25}{this.FechaRegistro:yyyy-MM-dd HH:mm:ss}");

            return sb.ToString();
        }
    }
} 
